import { Ionicons } from "@expo/vector-icons";
import { FormProvider, useForm, useFormContext } from "react-hook-form";
import { StyleSheet, Text, View } from "react-native";
import PrimaryButton from "../components/PrimaryButton";
import PasswordTextInput, {
  PasswordTextInputProps,
} from "../components/input/PasswordTextInput";

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

interface PasswordInputWithRequirementsProps extends PasswordTextInputProps {
  requirements: Requirement[];
}

const PasswordInputWithRequirements = (
  props: PasswordInputWithRequirementsProps
) => {
  const { requirements } = props;
  const formContext = useFormContext();
  const password = formContext.watch("password");

  const metRequirements = requirements.filter((requirement) =>
    requirement.re.test(password)
  );

  const requirementsFeedbackDisplay = requirements.map((requirement) => (
    <PasswordRequirement
      key={requirement.label}
      text={requirement.label}
      meets={requirement.re.test(password)}
    />
  ));

  return (
    <View style={styles.container}>
      <PasswordTextInput {...props} />
      <StrengthMeter
        numOfRequirements={props.requirements.length}
        numOfMetRequirements={metRequirements.length}
      />
      <View style={styles.requirements}>{requirementsFeedbackDisplay}</View>
    </View>
  );
};

const styles = StyleSheet.create({
  requirementText: {
    fontSize: 16,
  },
  container: {
    gap: 10,
  },
  requirements: {
    gap: 6,
  },
  meets: {
    color: "green",
  },
  fails: {
    color: "red",
  },
});

export default PasswordInputWithRequirements;
