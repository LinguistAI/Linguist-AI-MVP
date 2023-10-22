import { StyleSheet, TextInput, TextInputProps, View, Text } from "react-native";

import Colors from "../theme/colors";
import { useController, useFormContext } from "react-hook-form";

interface PrimaryTextInputProps extends TextInputProps {
    name: string;
    label: string;
    rules: any;
    defaultValue: string;
}

const PrimaryTextInput = (props: PrimaryTextInputProps) => {

    const { name } = props;
    
    const formContext = useFormContext();
    
    if (!formContext || !name) {
      const msg = !formContext ? "TextInput must be wrapped by the FormProvider" : "Name must be defined"
        console.error(msg)
      return null
    }
    
    return <ControlledInput {...props} />;
    
};

const ControlledInput = (props: PrimaryTextInputProps) => {


    const { field } = useController({name: props.name, rules: props.rules, defaultValue: props.defaultValue});

    return (
        <View style={styles.container}>
            {props.label && (<Text style={styles.label}>{props.label}</Text>)}
              <View style={styles.inputContainer}>
              <TextInput
                style={styles.input}
                onChangeText={field.onChange}
                onBlur={field.onBlur}
                value={field.value}
                {...props}
              />
            </View>
          </View>
    )
}

const styles = StyleSheet.create({
    container: {

    },
    label: {

    },
    inputContainer: {
    },  
    input: {
      height: 60,
      margin: 12,
      borderWidth: 1,
      padding: 10,
      borderColor: Colors.gray[300],
      color: Colors.gray[600],
      borderRadius: 16,
    },
  });

export default PrimaryTextInput;