# Smart Deals SA — Android starter project

This is a native Android (Kotlin + Jetpack Compose) starter app for Smart Deals SA.

## Included in version 1
- Search and category filters
- Starter product idea cards, plus the first Check It SA affiliate product
- Product detail dialog
- Android share sheet
- Affiliate disclosure wording
- GitHub Actions workflow to build a debug APK

## Important before public launch
The five sample products in `MainActivity.kt` are starter examples, not live offers. The Car Jump Starter & Tyre Inflator entry uses the Check It SA affiliate URL supplied by the app owner; its price is deliberately not hardcoded.
Replace them with products from affiliate programmes that have approved your account.
Add only your own approved affiliate URLs. Verify prices, availability, seller identity,
commission and payout terms. Do not invent reviews, discounts or earnings.

This first version has no backend, account system, live retailer feed, click analytics,
or admin dashboard. Those require a later development phase.

## Build an APK using a free GitHub Actions workflow
1. Create a GitHub account and a new **public** repository named `SmartDealsSA`.
2. On your phone, upload the contents of this project ZIP into the repository. Keep the folder structure.
3. Open the repository's **Actions** tab and enable workflows if prompted.
4. Run **Build Smart Deals SA APK** (or push a commit to `main`).
5. When the workflow finishes, open the run and download the `SmartDealsSA-debug-apk` artifact.
6. Extract the ZIP artifact on your Android phone and install the APK. Android may ask you to allow installation from that source.

A debug APK is for testing. A signed release build and privacy/legal review are needed before public distribution.
Google Play developer registration is not free. You can test-install the APK without publishing it to Google Play.

## App identity
- Display name: Smart Deals SA
- Application ID: `za.co.smartdealssa`
- Minimum Android version: Android 6.0 (API 23)
