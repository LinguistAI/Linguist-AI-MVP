import { Ionicons } from "@expo/vector-icons";
import { useState } from "react";
import { SafeAreaView, StyleSheet, Text, View } from "react-native";
import ActionIcon from "../components/ActionIcon";
import MultilineTextInput from "../components/input/MultilineTextInput";
import Colors from "../theme/colors";

const ChatScreen = () => {
  const [text, setText] = useState("");

  const handleSend = () => {
    console.log("send");
  };

  return (
    <SafeAreaView style={styles.safeArea}>
      <View style={styles.outerContainer}>
        <View style={styles.innerBorder}>
          <View style={styles.innerContainer}>
            <View style={{ flex: 8 }}>
              <MultilineTextInput
                onChangeText={(text) => setText(text)}
                value={text}
                maxHeight={40}
              />
            </View>
            <View style={{ flex: 1 }}>
              <ActionIcon
                icon={
                  <Ionicons name="send" size={24} color={Colors.primary[600]} />
                }
                onPress={handleSend}
              />
            </View>
          </View>
        </View>
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  safeArea: {
    flex: 1,
  },
  outerContainer: {
    flex: 1,
    justifyContent: "flex-end",
    marginHorizontal: 16,
    marginVertical: 18,
  },
  innerContainer: {
    flexDirection: "row",
    alignItems: "center",
    columnGap: 10,
  },
  innerBorder: {
    borderWidth: 2,
    borderColor: Colors.gray[600],
    borderRadius: 48,
    padding: 16,
  },
});

export default ChatScreen;
