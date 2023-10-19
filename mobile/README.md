# Development Guidelines

## File Structure

- /api: This folder contains files related to API integration and management, such as API service files and configurations. We will use the axios library to make API calls. We will also use the axios interceptors to handle the API calls' common functionalities, such as adding the authorization header to the request and handling the response and error. Along with axios, we will use react-query to manage the API calls' state and cache the API responses.

- /assets: This folder holds static assets like images, fonts, and other resources used within the application.

- /containers: Components that contain application/business logic are placed in the containers folder. Containers use the components from the components folder to render the UI. Containers are also responsible for managing the state of the application.

- /components: Presentational components render the visible UI, and should not contain any application-logic. Ideally, their only input is their props, so they could be used in a different app without any major modification. Presentational components are often referred to as "components", in constrast with "containers".

- /navigation: This folder holds the files related to the navigation of the application, including the setup of routing and navigation logic.

- /hooks: This folder contains custom React hooks, which are used to share logic between different components.

- /reducers: The reducers folder reducer hooks.

- /screens: The screens folder includes the main screens of the application, each representing a unique page or view.

- /theme: This folder contains files related to the application's theming, such as styles, colors, and other design-related configurations.

- /utils: The utils folder comprises utility functions and helper classes that provide commonly used functionalities throughout the application.
