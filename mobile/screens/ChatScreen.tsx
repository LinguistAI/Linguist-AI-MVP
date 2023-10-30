import { FlatList, SafeAreaView, StyleSheet, Text, View } from "react-native";
import ChatMessage from "../components/ChatMessage";
import ChatTextInputContainer from "../containers/Chat/ChatTextInputContainer";

const ChatScreen = () => {
  // TODO: Add a FlatList to render the messages

  return (
    <SafeAreaView style={styles.safeArea}>
      <View style={styles.messagesContainer}>
        <ChatMessage isSent={false} message="Heyyo" />
        <ChatMessage isSent={true} message="Heyyoooooo" />
        <ChatMessage isSent={false} message="Ayoooo" />
        <ChatMessage isSent={true} message="Duuuuudeee!" />
      </View>
      <View style={styles.textInputContainer}>
        <ChatTextInputContainer />
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  safeArea: {
    flex: 1,
  },
  textInputContainer: {
    flex: 1,
    justifyContent: "flex-end",
    marginHorizontal: 16,
    marginVertical: 18,
  },
  messagesContainer: {
    flex: 1,
    marginHorizontal: 16,
    marginVertical: 50,
  },
});

export default ChatScreen;
