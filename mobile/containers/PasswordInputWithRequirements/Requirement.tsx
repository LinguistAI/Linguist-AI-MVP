import { Ionicons } from "@expo/vector-icons";
import { StyleSheet, Text, View } from "react-native";

export type Requirement = {
  re: RegExp;
  label: string;
};

interface RequirementProps {
  meets: boolean;
  text: string;
}
const PasswordRequirement = (props: RequirementProps) => {
  const { meets, text } = props;

  return (
    <View>
      <Text
        style={[styles.requirementText, meets ? styles.meets : styles.fails]}
      >
        {meets ? (
          <Ionicons name="checkmark-circle" size={14} color="green" />
        ) : (
          <Ionicons name="close-circle" size={14} color="red" />
        )}
        {text}
      </Text>
    </View>
  );
};

const styles = StyleSheet.create({
  meets: {
    color: "green",
  },
  fails: {
    color: "red",
  },
  requirementText: {
    fontSize: 16,
  },
});

export default PasswordRequirement;
