import { Platform, Pressable, StyleSheet, Text, View } from "react-native";
import Colors from "../theme/colors";

interface SecondaryButtonProps {
  children: React.ReactNode;
  onPress?: () => void;
}

const SecondaryButton = (props: SecondaryButtonProps) => {
  const { children, onPress } = props;

  return (
    <View style={styles.outerContainer}>
      <Pressable
        onPress={onPress}
        android_ripple={{ color: Colors.gray[100] }}
        style={({ pressed }) => {
          if (Platform.OS === "ios") {
            return [styles.innerContainer, pressed && styles.pressed];
          }
          return styles.innerContainer;
        }}
      >
        <Text style={styles.buttonText}>{children}</Text>
      </Pressable>
    </View>
  );
};

const styles = StyleSheet.create({
  outerContainer: {
    borderRadius: 8,
    borderWidth: 1.5,
    marginVertical: 8,
    overflow: "hidden",
    borderColor: Colors.primary[500],
    shadowColor: Colors.gray[900],
    shadowOpacity: 0.5,
    shadowRadius: 8,
    shadowOffset: {
      width: 0,
      height: 12,
    },
    elevation: 12,
  },
  innerContainer: {
    backgroundColor: "white",
    paddingVertical: 16,
    paddingHorizontal: 32,
  },
  buttonText: {
    color: Colors.primary[500],
    fontSize: 18,
    fontWeight: "bold",
    textAlign: "center",
  },
  pressed: {
    opacity: 0.75,
  },
});

export default SecondaryButton;
