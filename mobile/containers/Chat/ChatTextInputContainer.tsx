import { Ionicons } from "@expo/vector-icons";
import { useState } from "react";
import { StyleSheet, View } from "react-native";
import ActionIcon from "../../components/ActionIcon";
import MultilineTextInput from "../../components/input/MultilineTextInput";
import Colors from "../../theme/colors";

const ChatTextInputContainer = () => {
  const [text, setText] = useState("");

  const handleSend = () => {
    console.log("send");
  };
  return (
    <View style={styles.innerBorder}>
      <View style={styles.innerContainer}>
        <View style={{ flex: 8 }}>
          <MultilineTextInput
            onChangeText={(text) => setText(text)}
            value={text}
            maxHeight={60}
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
  );
};

const styles = StyleSheet.create({
  innerContainer: {
    flexDirection: "row",
    alignItems: "center",
    columnGap: 10,
  },
  innerBorder: {
    borderWidth: 2,
    borderColor: Colors.gray[600],
    borderRadius: 48,
    paddingHorizontal: 16,
    paddingVertical: 4,
  },
});

export default ChatTextInputContainer;
