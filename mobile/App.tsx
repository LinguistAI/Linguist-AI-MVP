import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { atom } from "jotai";
import { SafeAreaView } from "react-native";
import Notifications from "./containers/NotificationsContainer/Notifications";
import BottomNavigation from "./navigation/BottomNavigation";
import ForgotPasswordScreen from "./screens/ForgotPasswordScreen";
import LandingScreen from "./screens/LandingScreen";
import LoginScreen from "./screens/LoginScreen";
import RegisterScreen from "./screens/RegisterScreen";
import { CustomErrorBoundary } from "./screens/errors/ErrorBoundary";

const queryClient = new QueryClient();

export default function App() {
  const Stack = createNativeStackNavigator();

  return (
    <QueryClientProvider client={queryClient}>
      <CustomErrorBoundary>
        <NavigationContainer>
          <Stack.Navigator initialRouteName="Landing">
            <Stack.Screen
              name="Landing"
              component={LandingScreen}
              options={{ headerShown: false }}
            />
            <Stack.Screen name="Login" component={LoginScreen} />
            <Stack.Screen
              name="Main"
              component={BottomNavigation}
              options={{ headerShown: false }}
            />
            <Stack.Screen name="Register" component={RegisterScreen} />
            <Stack.Screen
              name="Forgot Password"
              component={ForgotPasswordScreen}
            />
          </Stack.Navigator>
          <Notifications />
        </NavigationContainer>
      </CustomErrorBoundary>
    </QueryClientProvider>
  );
}
