import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import {
  Button,
  Text,
  View,
} from 'react-native';

// Define Screens
const HomeScreen = ({ navigation }) => {
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text>Home Screen</Text>
      <Button
        title="Go to Jane's profile"
        onPress={() => navigation.navigate('Profile', { name: 'Jane' })}
      />
    </View>
  );
};

const ProfileScreen = ({ route }) => {
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text>This is {route.params.name}'s profile</Text>
    </View>
  );
};

// Create Stack Navigator
const Stack = createNativeStackNavigator();

const MyStack = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen
        name="Home"
        component={HomeScreen}
        options={{ title: 'Welcome' }}
      />
      <Stack.Screen
        name="Profile"
        component={ProfileScreen}
        />
    </Stack.Navigator>
  );
};

// Main App Component
function App(): React.JSX.Element {
  return (
    <NavigationContainer>
      <MyStack />
    </NavigationContainer>
  );
}

export default App;


// import React from 'react';
// import {NavigationContainer} from '@react-navigation/native';
//
// import {createNativeStackNavigator} from '@react-navigation/native-stack';
//
// import {
//   Button,
//   SafeAreaView,
//   ScrollView,
//   StatusBar,
//   StyleSheet,
//   Text,
//   useColorScheme,
//   View,
// } from 'react-native';
//
// import {
//   Colors,
//   DebugInstructions,
//   Header,
//   ReloadInstructions,
// } from 'react-native/Libraries/NewAppScreen';
//
// function App(): React.JSX.Element {
//   const isDarkMode = useColorScheme() === 'dark';
//
//   const backgroundStyle = {
//     backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
//   };
//
//   return (
//       <NavigationContainer>
//     <SafeAreaView style={backgroundStyle}>
//       <StatusBar
//         barStyle={isDarkMode ? 'light-content' : 'dark-content'}
//         backgroundColor={backgroundStyle.backgroundColor}
//       />
//       <ScrollView
//         contentInsetAdjustmentBehavior="automatic"
//         style={backgroundStyle}>
//         <Header />
//         <View
//           style={{
//             backgroundColor: isDarkMode
//               ? Colors.black
//               : Colors.white,
//             padding: 24,
//           }}>
//           <Text style={styles.title}>Step One</Text>
//           <Text>
//             Edit <Text style={styles.bold}>App.tsx</Text> to
//             change this screen and see your edits.
//           </Text>
//           <Text style={styles.title}>See your changes</Text>
//           <ReloadInstructions />
//           <Text style={styles.title}>Debug</Text>
//           <DebugInstructions />
//         </View>
//       </ScrollView>
//     </SafeAreaView>
//     </NavigationContainer>
//   );
// }
//
// const styles = StyleSheet.create({
//   title: {
//     fontSize: 24,
//     fontWeight: '600',
//   },
//   bold: {
//     fontWeight: '700',
//   },
// });
//
//
// const Stack = createNativeStackNavigator();
//
// const MyStack = () => {
//   return (
//     <NavigationContainer>
//       <Stack.Navigator>
//         <Stack.Screen
//           name="Home"
//           component={HomeScreen}
//           options={{title: 'Welcome'}}
//         />
//         <Stack.Screen name="Profile" component={ProfileScreen} />
//       </Stack.Navigator>
//     </NavigationContainer>
//   );
// };
//
// const HomeScreen = ({navigation}) => {
//   return (
//     <Button
//       title="Go to Jane's profile"
//       onPress={() =>
//         navigation.navigate('Profile', {name: 'Jane'})
//       }
//     />
//   );
// };
//
// const ProfileScreen = ({navigation, route}) => {
//   return <Text>This is {route.params.name}'s profile</Text>;
// };
//
//
// export default App;