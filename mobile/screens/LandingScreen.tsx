import { StyleSheet, View } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import SecondaryButton from "../components/SecondaryButton";

const LandingScreen = () => {
  return (
    <View style={styles.container}>
      <PrimaryButton>LOG IN</PrimaryButton>
      <SecondaryButton>CREATE AN ACCOUNT</SecondaryButton>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginVertical: 32,
    padding: 20,
  },
});

export default LandingScreen;
