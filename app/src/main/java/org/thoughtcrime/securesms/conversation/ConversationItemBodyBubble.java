package org.thoughtcrime.securesms.conversation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.components.Outliner;
import org.thoughtcrime.securesms.components.QuoteView;
import org.thoughtcrime.securesms.util.Projection;
import org.thoughtcrime.securesms.util.Util;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ConversationItemBodyBubble extends LinearLayout {

  @Nullable private List<Outliner>        outliners = Collections.emptyList();
  @Nullable private OnSizeChangedListener sizeChangedListener;

  private ClipProjectionDrawable clipProjectionDrawable;
  private Projection             quoteViewProjection;
  private Projection             videoPlayerProjection;

  private QuoteView quoteView;
  private Bitmap bitmap;
  private Canvas shit;
  private BitmapDrawable holepunchedBackground;
  private Paint holepunchPaint;
  private Projection holepunch;

  public ConversationItemBodyBubble(Context context) {
    super(context);
    setLayoutTransition(new BodyBubbleLayoutTransition());
  }

  public ConversationItemBodyBubble(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    setLayoutTransition(new BodyBubbleLayoutTransition());
  }

  public ConversationItemBodyBubble(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setLayoutTransition(new BodyBubbleLayoutTransition());
  }

  public void setQuoteViewHolepunch() {
    quoteView = findViewById(R.id.quote_view);

    holepunchPaint = new Paint();
    holepunchPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    holepunchPaint.setColor(Color.CYAN);
  }

  public void setOutliners(@NonNull List<Outliner> outliners) {
    this.outliners = outliners;
  }

  public void setOnSizeChangedListener(@Nullable OnSizeChangedListener listener) {
    this.sizeChangedListener = listener;
  }

  @Override
  public void setBackground(Drawable background) {
    clipProjectionDrawable = new ClipProjectionDrawable(background);

    clipProjectionDrawable.setProjections(getProjections());
    super.setBackground(clipProjectionDrawable);
  }

  public void setQuoteViewProjection(@Nullable Projection quoteViewProjection) {
    if (this.quoteViewProjection != null) {
      this.quoteViewProjection.release();
    }

    this.quoteViewProjection = quoteViewProjection;
    clipProjectionDrawable.setProjections(getProjections());
  }

  public void setVideoPlayerProjection(@Nullable Projection videoPlayerProjection) {
    if (this.videoPlayerProjection != null) {
      this.videoPlayerProjection.release();
    }

    this.videoPlayerProjection = videoPlayerProjection;
    clipProjectionDrawable.setProjections(getProjections());
  }

  public @Nullable Projection getVideoPlayerProjection() {
    return videoPlayerProjection;
  }

  public @NonNull Set<Projection> getProjections() {
    return Stream.of(quoteViewProjection, videoPlayerProjection)
                 .filterNot(Objects::isNull)
                 .collect(Collectors.toSet());
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (holepunchPaint != null) {
      // Since ConversationItems live in a RecyclerView, calling
      // super.onDraw() will draw over everything that the RecyclerView's
      // ItemDecorator.onDraw() drew. In the case of gradient chat bubbles,
      // this would irrecoverably remove the gradient when the chat bubble's
      // true background is drawn.
      // Our solution is pretty dumb. We have to programmatically cut a window
      // in the shape of the QuoteView in the chat bubble's background. To do
      // this, first we extract the chat bubble's background as a drawable,
      // then we paint it onto a canvas, then we punch a QuoteView-shaped hole
      // onto the canvas, and then we take the BitMap out of the canvas, and
      // then we turn that BitMap into a new Drawable, and then we shove that
      // new Drawable into the chat bubble's background. Is it dumb as shit?
      // Yes. But does it work? Also yes.
      // Anyway, if you want to save CPU cycles then just don't use gradient
      // chat bubbles and we'll call it a draw.
      if (bitmap == null) {
        // if this is the first time we're drawing, then we're ready to initialise.
        bitmap                = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        shit                  = new Canvas(bitmap);
        holepunchedBackground = new BitmapDrawable(getResources(), bitmap);
      }
      this.getBackground().draw(shit);
      this.holepunch = Optional.of(quoteView.getProjection(this))
                               .map(quoteViewToParent -> {
                                 Log.e("yolo", quoteViewToParent.toString());
                                 float translationX = Util.halfOffsetFromScale(quoteView.getWidth(), quoteView.getScaleX());
                                 float translationY = Util.halfOffsetFromScale(quoteView.getHeight(), quoteView.getScaleY());

                                 return
                                     quoteViewToParent.scale(quoteView.getScaleX())
                                                      .translateX(translationX)
                                                      .translateY(translationY);
                               }).get();
      shit.drawPath(holepunch.getPath(), holepunchPaint);
      this.setBackground(holepunchedBackground);
    }
    super.onDraw(canvas);
  }

  @Override
  public void onDrawForeground(Canvas canvas) {
    super.onDrawForeground(canvas);

    if (Util.isEmpty(outliners)) return;

    for (Outliner outliner : outliners) {
      outliner.draw(canvas, 0, getMeasuredWidth(), getMeasuredHeight(), 0);
    }
  }

  @Override
  protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
    bitmap = null;
    invalidate();
    if (sizeChangedListener != null) {
      post(() -> {
        if (sizeChangedListener != null) {
          sizeChangedListener.onSizeChanged(width, height);
        }
      });
    }
  }

  public interface OnSizeChangedListener {
    void onSizeChanged(int width, int height);
  }
}

