package com.dkass.calculator;

public class Logic {

    public static String number_left = "";
    public static String number_right = "";
    public static String operation = "";

    public static float square_root(float a, float test)
    {
        if (Math.abs(test * test - a) < 0.0001)
        {
            return test;
        }
        float h = a / test;
        return square_root(a, (test + h) / 2.0f);
    }
    public static float square_root(float a)
    {
        return square_root(a, a / 2.0f);
    }

    public static String output_text()
    {
        String temp_string = "";

        if (!number_left.equals(""))
        {
            temp_string = "(" + number_left + ") ";
        }

        temp_string = temp_string + operation;

        if (!number_right.equals(""))
        {
            temp_string = temp_string + " (" + number_right + ")";
        }

        return temp_string;
    }

    public static void input_number(String input)
    {
        if (operation == "")
        {
//            number_left = number_left.Insert(number_left.Length, input);
            number_left = number_left + input;
        }
        else
        {
//            number_right = number_right.Insert(number_right.Length, input);
            number_right = number_right + input;
        };

    }

    public static void input_operation(String input)
    {
        if(number_right.equals("") && operation.equals(input))
        {
            operation = "";
        }
        else if (number_right.equals(""))
        {
            operation = input;
        }
        else
        {
            operation = input;
            input_solve();
        }
    }

    public static void input_sign()
    {
        if (number_right.equals(""))
        {
            if (number_left.charAt(0) == '-')
            {
//                number_left = number_left.Remove(0, 1);
                number_left = number_left.substring(1);
            }
            else
            {
//                number_left = number_left.Insert(0, "-");
                number_left = "-"+number_left;
            }
        }
        else
        {
            if (number_right.charAt(0) == '-')
            {
//                number_right = number_right.Remove(0, 1);
                number_right = number_right.substring(1);
            }
            else
            {
//                number_right = number_right.Insert(0, "-");
                number_right = "-"+number_right;
            }
        }

    }

//    public static void input_root()
//    {
//        if (Convert.ToSingle(number_left) < 0)
//        {
//            number_left = "err";
//        }
//        else if (number_left != "" && operation =="" && number_right == "")
//        {
//            number_left = Convert.ToString(square_root(Convert.ToSingle(number_left)));
//        }
//    }

    public static void input_comma()
    {
        if (!number_left.equals("") && operation.equals("") && number_right.equals("") && !number_left.contains("."))
        {
//            number_left = number_left.Insert(number_left.Length, ",");
            number_left = number_left + ".";
        }
        else if(!number_left.equals("") && !operation.equals("") && !number_right.equals("") && !number_right.contains("."))
        {
//            number_right = number_right.Insert(number_right.Length, ",");
            number_right = number_right + ".";
        }
    }

    public static void input_solve()
    {
        if (!number_left.equals("") && !operation.equals("") && !number_right.equals(""))
        {
            switch (operation)
            {
                case "-":
//                    number_left = Convert.ToString(Convert.ToSingle(number_left) - Convert.ToSingle(number_right));
                    number_left = String.valueOf(Float.parseFloat(number_left) - Float.parseFloat(number_right));
                    break;
                case "+":
//                    number_left = Convert.ToString(Convert.ToSingle(number_left) + Convert.ToSingle(number_right));
                    number_left = String.valueOf(Float.parseFloat(number_left) + Float.parseFloat(number_right));
                    break;
                case "/": {
//                    number_left = Convert.ToString(Convert.ToSingle(number_left) / Convert.ToSingle(number_right));
                    if(number_right.equals("0") || Float.parseFloat(number_right) == 0)
                        number_left = "can't divide by zero!";
                    else
                        number_left = String.valueOf(Float.parseFloat(number_left) / Float.parseFloat(number_right));
                    break;
                }
                case "*":
//                    number_left = Convert.ToString(Convert.ToSingle(number_left) * Convert.ToSingle(number_right));
                    number_left = String.valueOf(Float.parseFloat(number_left) * Float.parseFloat(number_right));
                    break;
                default:
                    number_left = "error";
                    break;
            }
            number_right = "";
            operation = "";
        }
        else
        {
            // operation = "error";
        }
    }

    public static void input_clear()
    {
        number_left = "";
        number_right = "";
        operation = "";
        return;
    }

    public static void input_floor()
    {
        if (!number_left.equals("") && !operation.equals("") && !number_right.equals(""))
        {
            input_solve();
        }

        if(!number_left.equals("") && operation.equals("") && number_right.equals(""))
        {
//            number_left = Convert.ToString(Math.Floor(Convert.ToSingle(number_left)));
//            number_left = String.valueOf(Float.parseFloat(number_left) * Float.parseFloat(number_right));
            number_left = String.valueOf(Math.floor(Float.parseFloat(number_left)));
        }
    }

    public static void input_ceiling()
    {
        if (!number_left.equals("") && !operation.equals("") && !number_right.equals(""))
        {
            input_solve();
        }

        if (!number_left.equals("") && operation.equals("") && number_right.equals(""))
        {
//            number_left = Convert.ToString(Math.Ceiling(Convert.ToSingle(number_left)));
            number_left = String.valueOf(Math.ceil(Float.parseFloat(number_left)));
        }
    }
}
