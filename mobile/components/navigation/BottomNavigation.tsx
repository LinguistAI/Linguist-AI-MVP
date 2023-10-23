import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import LandingScreen from "../../screens/LandingScreen";
import HomeScreen from "../../screens/HomeScreen";
import ChatScreen from "../../screens/ChatScreen";
import LeaderboardScreen from "../../screens/LeaderboardScreen";
import ProfileScreen from "../../screens/ProfileScreen";
import { Settings } from "react-native";
import SettingsScreen from "../../screens/SettingsScreen";

const Tab = createBottomTabNavigator();

const BottomNavigation = () => {
  return (
    <Tab.Navigator>
      <Tab.Screen name="Home" component={HomeScreen} />
      <Tab.Screen name="Chat" component={ChatScreen} />
      <Tab.Screen name="Leaderboard" component={LeaderboardScreen} />
      <Tab.Screen name="Profile" component={ProfileScreen} />
      <Tab.Screen name="Settings" component={SettingsScreen} />
    </Tab.Navigator>
  );
};

export default BottomNavigation;
