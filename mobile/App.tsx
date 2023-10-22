import { SafeAreaView } from "react-native";
import LandingScreen from "./screens/LandingScreen";
import LoginScreen from "./screens/LoginScreen";

export default function App() {
  return (
    <SafeAreaView style={{ flex: 1 }}>
      <LoginScreen />
    </SafeAreaView>
  );
}
