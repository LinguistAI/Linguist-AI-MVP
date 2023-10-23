import {
  View,
  Image,
  StyleSheet,
  Text,
  Touchable,
  TouchableWithoutFeedback,
} from "react-native";
import Colors from "../theme/colors";
import * as ImagePicker from "expo-image-picker";
import { useState } from "react";

const ProfileScreen = () => {
  const [profileImage, setProfileImage] = useState(
    "https://thispersondoesnotexist.com"
  );

  const pickImage = async () => {
    // No permissions request is necessary for launching the image library
    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      allowsEditing: true,
      aspect: [4, 3],
      quality: 1,
    });

    console.log(result);

    if (!result.canceled) {
      setProfileImage(result.assets[0].uri);
    }
  };

  return (
    <View>
      <View style={styles.topSection} />
      <TouchableWithoutFeedback onPress={pickImage}>
        <Image
          source={{
            uri: profileImage,
          }}
          style={styles.profileImage}
        />
      </TouchableWithoutFeedback>
      <View style={styles.userInformation}>
        <Text style={styles.userName}>Tolga Özgün</Text>
        <Text style={styles.userDescription}>A mantra goes here</Text>
      </View>

      <View style={styles.activityContainer}>
        <Text style={styles.activityTitle}>Activity</Text>
        <Text style={styles.activityNotFound}>No activity found</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  topSection: {
    backgroundColor: Colors.primary[200],
    height: 200,
    width: "100%",
  },
  profileImage: {
    width: 200,
    height: 200,
    marginTop: -130,
    borderRadius: 200 / 2,
    alignSelf: "center",
    borderWidth: 6,
    borderColor: "white",
  },
  userInformation: {
    marginTop: 16,
    alignItems: "center",
    gap: 6,
  },
  userName: {
    fontSize: 24,
    fontWeight: "bold",
  },
  userDescription: {
    fontSize: 16,
  },
  activityContainer: {
    marginTop: 32,
    alignItems: "center",
    gap: 8,
  },
  activityTitle: {
    fontSize: 24,
    fontWeight: "bold",
  },
  activityNotFound: {
    fontSize: 16,
    color: Colors.gray[600],
  },
});
export default ProfileScreen;
