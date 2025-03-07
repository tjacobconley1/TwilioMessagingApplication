const axios = require('axios');

const testSendSMS = async () => {
  try {
    const response = await axios.post('http://localhost:3000/send_sms', {
      to: '+15555555555',  // Replace with the recipient's phone number
      body: 'Hello, this is a test message from Twilio!'
    });

    console.log('Response:', response.data);
  } catch (error) {
    console.error('Error:', error.response ? error.response.data : error.message);
  }
};

testSendSMS();
