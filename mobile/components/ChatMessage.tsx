import { StyleSheet, Text, View } from "react-native";
import Colors from "../theme/colors";

interface ChatMessageProps {
  message: string;
  isSent: boolean;
}

const ChatMessage = (props: ChatMessageProps) => {
  const { message, isSent } = props;

  return (
    <View style={[styles.container, isSent ? styles.sent : styles.received]}>
      <Text style={styles.message}>{message}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    maxWidth: "80%",
    borderRadius: 8,
    padding: 8,
    marginVertical: 4,
  },
  sent: {
    alignSelf: "flex-end",
    backgroundColor: Colors.primary[600],
  },
  received: {
    alignSelf: "flex-start",
    backgroundColor: Colors.gray[700],
  },
  message: {
    color: "white",
    fontSize: 16,
  },
});

export default ChatMessage;
