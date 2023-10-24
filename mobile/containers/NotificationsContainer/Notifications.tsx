import { StyleSheet, View } from "react-native";
import Animated, { FadeInLeft, FadeOutLeft } from "react-native-reanimated";
import useNotifications from "../../hooks/useNotifications";
import Notification from "./Notification";

const Notifications = () => {
  const { notifications } = useNotifications();

  return (
    <View style={styles.container}>
      {notifications.map((notification, index) => (
        <Animated.View
          key={notification.id}
          entering={FadeInLeft}
          exiting={FadeOutLeft}
        >
          <Notification notification={notification} />
        </Animated.View>
      ))}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    position: "absolute",
    padding: 20,
    bottom: 10,
    left: 0,
    right: 0,
    zIndex: 9999,
    gap: 12,
  },
});

export default Notifications;
