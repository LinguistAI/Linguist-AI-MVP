import { FormProvider, useForm, useFormContext } from "react-hook-form";
import { StyleSheet, Text, View } from "react-native";
import PasswordTextInput, {
  PasswordTextInputProps,
} from "../../components/input/PasswordTextInput";
import PasswordRequirement, { Requirement } from "./Requirement";
import StrengthMeter from "./StrengthMeter";

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
  container: {
    gap: 10,
  },
  requirements: {
    gap: 6,
  },
});

export default PasswordInputWithRequirements;
