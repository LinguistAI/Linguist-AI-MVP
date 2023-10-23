import React, { useState } from "react";
import {
  SafeAreaView,
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  ViewStyle,
  StyleProp,
} from "react-native";
import Colors from "../theme/colors";

interface ButtonGroupProps {
  buttons: string[];
  selection: number;
  setSelection: React.Dispatch<React.SetStateAction<number>>;
  style: StyleProp<ViewStyle>;
}

const ButtonGroup = (props: ButtonGroupProps) => {
  return (
    <SafeAreaView style={[styles.container, props.style]}>
      <View style={styles.btnGroup}>
        {props.buttons.map((text, index) => {
          return (
            <TouchableOpacity
              key={text}
              style={[
                styles.btn,
                props.selection === index ? { backgroundColor: "white" } : {},
              ]}
              onPress={() => props.setSelection(index)}
            >
              <Text
                style={[
                  styles.btnText,
                  props.selection === index
                    ? { color: Colors.primary[400] }
                    : {},
                ]}
              >
                {text}
              </Text>
            </TouchableOpacity>
          );
        })}
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    width: "100%",
  },
  btnGroup: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    borderRadius: 16,
    backgroundColor: Colors.gray[300],
  },
  btn: {
    flex: 1,
    backgroundColor: Colors.gray[300],
    width: 100,
    borderRadius: 16,
    borderWidth: 0.25,
    borderColor: Colors.gray[300],
  },
  btnText: {
    textAlign: "center",
    color: Colors.gray[600],
    paddingVertical: 16,
    fontSize: 14,
  },
});

export default ButtonGroup;
