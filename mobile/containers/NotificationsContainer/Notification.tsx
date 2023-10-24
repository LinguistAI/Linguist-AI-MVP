import { StyleSheet, Text, View } from "react-native";
import { NotificationObject } from "../../hooks/useNotifications";
import Colors from "../../theme/colors";

interface NotificationProps {
  notification: NotificationObject;
}

const Notification = (props: NotificationProps) => {
  const { notification } = props;

  const getNotificationBgStyle = () => {
    switch (notification.type) {
      case "info":
        return styles.infoBgColor;
      case "success":
        return styles.successBgColor;
      case "error":
        return styles.errorBgColor;
      default:
        return styles.infoBgColor;
    }
  };

  return (
    <View style={[styles.container, getNotificationBgStyle()]}>
      <View style={styles.notificationCard}>
        <Text style={[styles.title, { color: notification?.titleColor }]}>
          {notification.title}
        </Text>
        <Text style={[styles.body, { color: notification?.bodyColor }]}>
          {notification.body}
        </Text>
      </View>
    </View>
  );
};

export default Notification;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    zIndex: 9999,
    borderRadius: 10,
  },
  notificationCard: {
    padding: 20,
    marginBottom: 10,
  },
  title: {
    fontWeight: "bold",
    fontSize: 18,
    marginBottom: 10,
  },
  body: {
    fontSize: 16,
  },
  infoBgColor: {
    backgroundColor: Colors.blue[500],
  },
  successBgColor: {
    backgroundColor: Colors.green[500],
  },
  errorBgColor: {
    backgroundColor: Colors.red[600],
  },
});
