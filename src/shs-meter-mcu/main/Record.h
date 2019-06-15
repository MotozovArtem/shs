struct Record
{
    float value;
    String serialNumber = "abc1234512";
    String result = "";

    void toJson()
    {
        result.concat("{\"value\":");
        result.concat(value);
        result.concat(",\"serialNumber\":\"");
        result.concat(serialNumber);
        result.concat("\"}");
    }
};

// {"value":1234567890,"serialNumber":"12345678901234567890123465789012"} 70 symbols