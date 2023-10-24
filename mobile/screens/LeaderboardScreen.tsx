import { View, Image, StyleSheet, Text } from "react-native";
import Colors from "../theme/colors";
import { useState } from "react";
import ButtonGroup from "../components/ButtonGroup";

const avatarPlaceholderImg = require("../assets/profile-default.jpg");

const LeaderboardScreen = () => {
  const [selectedIndex, setSelectedIndex] = useState(1);
  const buttons = ["Global", "Friends"];

  let currentButton = buttons[selectedIndex];

  return (
    <View
      // colors={[, "#1da2c6", "#1695b7"]}
      style={styles.topSection}
    >
      {/* <Text style={{ fontSize: 25, color: "white" }}>Leaderboard</Text> */}
      <View style={styles.userInformation}>
        <Text style={styles.rankText}>1st</Text>
        <Image
          style={styles.profileImage}
          source={{
            uri: "https://thispersondoesnotexist.com",
          }}
          defaultSource={avatarPlaceholderImg}
        />
        <Text style={styles.pointsText}>40pts</Text>
      </View>

      <ButtonGroup
        buttons={buttons}
        selection={selectedIndex}
        setSelection={setSelectedIndex}
        style={{ height: 30 }}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  topSection: {
    backgroundColor: Colors.primary[200],
    padding: 15,
    paddingTop: 35,
    alignItems: "center",
    height: 200,
    width: "100%",
  },
  profileImage: {
    height: 60,
    width: 60,
    borderRadius: 60 / 2,
    alignSelf: "center",
    borderWidth: 1,
    borderColor: "white",
  },
  rankText: {
    color: "white",
    fontSize: 25,
    flex: 1,
    textAlign: "right",
    marginRight: 40,
  },
  pointsText: {
    color: "white",
    fontSize: 25,
    textAlign: "left",
    flex: 1,
    marginLeft: 40,
  },
  userInformation: {
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
    marginBottom: 15,
    marginTop: 20,
  },
});
export default LeaderboardScreen;
