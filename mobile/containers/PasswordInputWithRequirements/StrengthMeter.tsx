import { StyleSheet, View } from "react-native";

interface StrengthMeterProps {
  numOfRequirements: number;
  numOfMetRequirements: number;
}

const StrengthMeter = (props: StrengthMeterProps) => {
  const { numOfRequirements, numOfMetRequirements } = props;

  return (
    <View style={strengthMeterStyles.container}>
      <View
        style={[
          strengthMeterStyles.meter,
          { width: `${(numOfMetRequirements / numOfRequirements) * 100}%` },
        ]}
      />
    </View>
  );
};

const strengthMeterStyles = StyleSheet.create({
  container: {
    maxWidth: "100%",
    height: 10,
    borderRadius: 5,
    backgroundColor: "red",
  },
  meter: {
    height: "100%",
    borderRadius: 5,
    backgroundColor: "green",
  },
});

export default StrengthMeter;
