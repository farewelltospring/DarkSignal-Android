# What's different?

We make Signal look better on dark mode with the following features:
- `darkavatar`: Default profile icons (using the initials of your first and last name) are now rendered with a dark background with light letters.
- `darkquote`: The quote part of quoted messages now has the same colour as the original message that got quoted, irrespective of who's quoting it. So if your chat bubbles are green, and the other person's chat bubbles are blue, then quoting your chat bubbles will have a green-ish quote, and quoting the other person's chat bubbles will have a blue-ish quote. (This works with gradient chat bubbles too). The `dumb` branch is my attempt to do things, which had some visual bugs. The `old` branch is how Signal _used_ to do this, but they removed the feature for some reason, so I just brought it back.
- `snaptions`: Ok, I know this one has nothing to do with dark mode, but it basically just copies Snapchat's classic text captions.
- `slowbackups`: Also has nothing to do with dark mode. But it adds a setting for you to change the frequency that backups are made to a longer interval (weekly, monthly, quarterly, every 6 months, annually). This will appeal to people with large Signal databases who are trying to reduce wear on their phone's internal storage or SD card.
- `smooth-attach-menu`: Adds an animated transition when you open and close the keyboard pager (stickers/gifs/emoji keyboard) or the attachment menu, to achieve parity for when you open or close the regular system keyboard, and adds a small corresponding animation to the attachment button when you open/close the attachment menu.
- `darkest`: Makes the background of the app black instead of just dark gray.
----
# Signal Android 

Signal is a simple, powerful, and secure messenger.

Signal uses your phone's data connection (WiFi/3G/4G/5G) to communicate securely. Millions of people use Signal every day for free and instantaneous communication anywhere in the world. Send and receive high-fidelity messages, participate in HD voice/video calls, and explore a growing set of new features that help you stay connected. Signal’s advanced privacy-preserving technology is always enabled, so you can focus on sharing the moments that matter with the people who matter to you.

Currently available on the Play Store and [signal.org](https://signal.org/android/apk/).

<a href='https://play.google.com/store/apps/details?id=org.thoughtcrime.securesms&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height='80px'/></a>

## Contributing Bug reports
We use GitHub for bug tracking. Please search the existing issues for your bug and create a new one if the issue is not yet tracked!

https://github.com/signalapp/Signal-Android/issues

## Joining the Beta
Want to live life on the bleeding edge and help out with testing?

You can subscribe to Signal Android Beta releases here:
https://play.google.com/apps/testing/org.thoughtcrime.securesms

If you're interested in a life of peace and tranquility, stick with the standard releases.

## Contributing Code

If you're new to the Signal codebase, we recommend going through our issues and picking out a simple bug to fix in order to get yourself familiar. Also please have a look at the [CONTRIBUTING.md](https://github.com/signalapp/Signal-Android/blob/main/CONTRIBUTING.md), that might answer some of your questions.

For larger changes and feature ideas, we ask that you propose it on the [unofficial Community Forum](https://community.signalusers.org) for a high-level discussion with the wider community before implementation.

## Contributing Ideas
Have something you want to say about Signal projects or want to be part of the conversation? Get involved in the [community forum](https://community.signalusers.org).

Help
====
## Support
For troubleshooting and questions, please visit our support center!

https://support.signal.org/

## Documentation
Looking for documentation? Check out the wiki!

https://github.com/signalapp/Signal-Android/wiki

# Legal things
## Cryptography Notice

This distribution includes cryptographic software. The country in which you currently reside may have restrictions on the import, possession, use, and/or re-export to another country, of encryption software.
BEFORE using any encryption software, please check your country's laws, regulations and policies concerning the import, possession, or use, and re-export of encryption software, to see if this is permitted.
See <http://www.wassenaar.org/> for more information.

The U.S. Government Department of Commerce, Bureau of Industry and Security (BIS), has classified this software as Export Commodity Control Number (ECCN) 5D002.C.1, which includes information security software using or performing cryptographic functions with asymmetric algorithms.
The form and manner of this distribution makes it eligible for export under the License Exception ENC Technology Software Unrestricted (TSU) exception (see the BIS Export Administration Regulations, Section 740.13) for both object code and source code.

## License

Copyright 2013-2025 Signal Messenger, LLC

Licensed under the GNU AGPLv3: https://www.gnu.org/licenses/agpl-3.0.html

Google Play and the Google Play logo are trademarks of Google LLC.
