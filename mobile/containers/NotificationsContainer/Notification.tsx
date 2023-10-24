import { Ionicons } from "@expo/vector-icons";
import { StyleSheet, Text, View } from "react-native";
import ActionIcon from "../../components/ActionIcon";
import useNotifications, {
  NotificationObject,
} from "../../hooks/useNotifications";
import Colors from "../../theme/colors";
interface NotificationProps {
  notification: NotificationObject;
}

const Notification = (props: NotificationProps) => {
  const { remove } = useNotifications();
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
        <View style={styles.notificationCardContainer}>
          <Text style={[styles.title, { color: notification?.titleColor }]}>
            {notification.title}
          </Text>
          <Text style={[styles.body, { color: notification?.bodyColor }]}>
            {notification.body}
          </Text>
        </View>
        <View>
          <ActionIcon
            icon={<Ionicons name="close" size={24} color="black" />}
            onPress={() => remove(notification.id!)}
          />
        </View>
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
    flex: 1,
    flexDirection: "row",
    justifyContent: "space-between",
  },
  notificationCardContainer: {},
  messageSection: {},
  actionsSection: {},
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
