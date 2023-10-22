import { StyleSheet, View } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import PrimaryTextInput from "../components/PrimaryTextInput";

const LoginScreen = () => {
  return (
    <View style={styles.container}>
      <View style={styles.mainSection}>
        <View>
            <PrimaryTextInput 
                placeholder="Email"
            />
            <PrimaryTextInput 
                placeholder="Password"
            />
            <PrimaryButton>Login</PrimaryButton>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginVertical: 32,
    padding: 20,
  },
  mainSection: {
    flex: 5,
    justifyContent: "center",
  },
});

export default LoginScreen;
