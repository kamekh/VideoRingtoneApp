# VideoRingtoneApp

VideoRingtoneApp is a privacy-first Android app for creating and installing video ringtones on incoming calls.

## Product goals
- Provide a simple and safe way to choose a video and/or music track for incoming calls.
- Store content locally by default.
- Keep private mode as the default user experience.
- Let the user explicitly choose public mode if they want to share content later.
- Support Xiaomi / Poco / Android 10+ device behavior with a safe fallback flow.

## Core product flow
1. Welcome screen
2. Rules and terms screen
3. Privacy screen
4. Private/Public mode selection screen
5. Optional age gate for future public mode
6. App home screen

## Default behavior
- Private mode is enabled by default.
- In private mode, the user sees only their own ringtone library.
- No content is published automatically.
- No content is shared publicly without explicit consent.
- Public mode will be introduced only as a separate opt-in future feature.

## Rules and community policy
- Respect other users.
- No abuse, threats, harassment, discrimination, or hate speech.
- No violence, propaganda, or extremist content.
- No explicit or unsafe content outside of the proper age-restricted public mode.
- Public content is only available when the user chooses it intentionally.
- Violations may lead to warning, content removal, temporary block, or permanent block.

## Privacy and responsibility
- Users are responsible for their own public content.
- The platform is not responsible for user-generated content, but may remove, hide, or block content that violates the rules.
- Private mode keeps all content local and user-only.
- User-generated content is never published without explicit user action.

## Public mode policy (future stage)
- Public mode requires a clear opt-in by the user.
- Public mode requires age confirmation (18+).
- Public mode is unavailable by default.
- Public content is moderated and may be filtered or removed.

## Screens
- WelcomeScreen
- RulesScreen
- PrivacyScreen
- Private/Public Mode Selection Screen
- PrivacyModeSelectionScreen
- HomeScreen
- Ringtone preview screen
- My Ringtones screen
- Settings screen
- Security screen

## Main UX scenario
1. User opens app.
2. User reads rules.
3. User sees privacy explanation.
4. User chooses private mode by default.
5. User selects video / music.
6. User previews ringtone on a mock incoming call UI.
7. User saves ringtone locally.
8. User installs ringtone as incoming call tone.
9. User can modify or delete files inside the app.

## Future roadmap
### v1
- Local/private mode
- Video selection
- Music selection
- Preview and fit screen
- Save and install ringtone
- Minimal settings and protection

### v2
- Profile with local private library
- Security options: PIN / biometric
- Hidden/private storage mode
- Better device-fit logic for Xiaomi/Poco

### v3
- Public mode opt-in
- Age gate and moderation
- Public feed and content rules
- Reporting, warnings, and block system

### v4
- Likes, views, rankings, rewards, achievements
- Social layer and community features

## Important technical note
- Xiaomi / Poco / MIUI may restrict automatic ringtone installation.
- The app should provide a safe fallback: store the ringtone locally, allow preview, prompt user to install manually if automatic assignment is blocked by OEM restrictions.

## Security principles
- Local-first design
- No hidden publishing
- No public exposure by default
- Respectful user environment
- Clear rules before app use
- User-controlled visibility
