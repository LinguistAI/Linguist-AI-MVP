import { StyleSheet, View } from "react-native";
import useNotifications from "../../hooks/useNotifications";
import Notification from "./Notification";

const Notifications = () => {
  const { notifications } = useNotifications();

  console.log(notifications);
  return (
    <View style={styles.container}>
      {notifications.map((notification) => (
        <Notification key={notification.id} notification={notification} />
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
