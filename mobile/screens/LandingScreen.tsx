import { StyleSheet, Text, View } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import SecondaryButton from "../components/SecondaryButton";
import Title from "../components/Title";
import Colors from "../theme/colors";

const LandingScreen = () => {
  return (
    <View style={styles.container}>
      <View style={styles.logoContainer}>
        <Text style={styles.logoText}>Linguist AI</Text>
      </View>
      <View style={styles.landingSection}>
        <View>
          <Title fontSize="h2">Regular here?</Title>
          <Text style={styles.sectionDescription}>Get back on your path!</Text>
        </View>
        <View style={styles.sectionButton}>
          <PrimaryButton>LOG IN</PrimaryButton>
        </View>
      </View>
      <View>
        <View
          style={[
            styles.landingSection,
            {
              borderBottomColor: "black",
              borderBottomWidth: StyleSheet.hairlineWidth,
            },
          ]}
        />
      </View>
      <View style={styles.landingSection}>
        <View>
          <Title fontSize="h2">Just coming in?</Title>
          <Text style={styles.sectionDescription}>Start your journey now.</Text>
        </View>
        <View style={styles.sectionButton}>
          <SecondaryButton>CREATE AN ACCOUNT</SecondaryButton>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  logoContainer: {
    alignItems: "center",
    justifyContent: "center",
    flex: 2,
  },
  logoText: {
    fontSize: 48,
    fontWeight: "bold",
    color: Colors.primary[500],
  },
  container: {
    flex: 1,
    marginVertical: 32,
    padding: 20,
  },
  landingSection: {
    flex: 5,
    justifyContent: "center",
  },
  sectionDescription: {
    fontSize: 16,
    fontWeight: "400",
    marginTop: 6,
    textAlign: "center",
  },
  sectionButton: {
    marginTop: 18,
  },
});

export default LandingScreen;
