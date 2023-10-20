import { Platform, Pressable, StyleSheet, Text, View } from "react-native";
import Colors from "../theme/colors";

interface PrimaryButtonProps {
  children: React.ReactNode;
  onPress?: () => void;
}

const PrimaryButton = (props: PrimaryButtonProps) => {
  const { children, onPress } = props;

  return (
    <View style={styles.outerContainer}>
      <Pressable
        onPress={onPress}
        android_ripple={{ color: Colors.primary[600] }}
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
    marginVertical: 8,
    overflow: "hidden",
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
    backgroundColor: Colors.primary[500],
    paddingVertical: 16,
    paddingHorizontal: 32,
    elevation: 4,
  },
  buttonText: {
    color: "white",
    fontSize: 18,
    fontWeight: "bold",
    textAlign: "center",
    shadowColor: "black",
    shadowOpacity: 0.5,
    shadowRadius: 8,
    shadowOffset: {
      width: 0,
      height: 4,
    },
  },
  pressed: {
    opacity: 0.75,
  },
});

export default PrimaryButton;
