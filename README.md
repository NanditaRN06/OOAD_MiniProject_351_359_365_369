# Centralized Platform for Sharing Capstone Projects and Subject Feedback

## Authors
- PES2UG23CS351 - [Moulya K A](https://github.com/moulyajs)
- PES2UG23CS359 - [Najmus Seher](https://github.com/Sehar-12)
- PES2UG23CS365 - [Nandita R Nadig](https://github.com/NanditaRN06)
- PES2UG23CS369 - [Naveen S](https://github.com/nh-44)

## UI Overview

The mini project now includes a consistent Thymeleaf-based UI layer under `src/main/resources/templates` for the core user journeys:

- `login.html` for role-based sign in
- `register.html` for creating student or faculty accounts
- `student-dashboard.html` for student profile updates and project access
- `faculty-dashboard.html` for faculty profile updates and project access
- `projects-list.html` for the shared project board used by both dashboards
- `success.html` for the success/landing view used by the auth flow

## Route Map

- `/auth/login` opens the login page
- `/auth/register` opens the registration page
- `/student/dashboard?email=...` opens the student dashboard
- `/faculty/dashboard?email=...` opens the faculty dashboard
- `/student/view-projects` and `/faculty/view-projects` open the shared project board

## Notes

- The new screens follow one shared visual theme so the app stays consistent across the current branch.
- The existing controllers already point to the updated templates, so the UI can be pulled and extended by the rest of the team without changing the navigation contract.

## Shared UI Components

Reusable UI components are now maintained directly inside this workspace under:

- `capstone_project_sharing_platform/src/main/resources/templates/fragments/components.html`
- `capstone_project_sharing_platform/src/main/resources/static/css/ui-components.css`

All existing templates import these shared assets from the same directory tree, so UI updates can now be done centrally without duplicating inline CSS or scripts.