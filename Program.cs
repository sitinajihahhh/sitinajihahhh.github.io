using System;
using System.Collections.Generic;
using System.Formats.Asn1;
using System.IO.Pipelines;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Calculator
{
    class Program
    {
        static void Main(string[] args)
        {
            int num1;
            int num2;
            int result;
            string answer;

            Console.WriteLine("Hello, Welcome to the calculator program!");
            Console.WriteLine("Please enter your first number.");

            num1 = Convert.ToInt32(Console.ReadLine());

            Console.WriteLine("Please enter your second number.");

            num2 = Convert.ToInt32(Console.ReadLine());

            Console.WriteLine("What type of operation would you like to do?");
            System.Console.WriteLine("Please enter A for addit45ion, S for substraction, M for multiplication or D for division.");

            answer = Console.ReadLine();

            if (answer == "A")
            {
                result = num1 + num2;
            }
            else if (answer == "S")
            {
                result = num1 - num2;
            }
            else if (answer == "M")
            {
                result = num1 * num2;
            }
            else
            {
                result = num1 / num2;
            }

            System.Console.WriteLine("The result is "+ result);
            System.Console.WriteLine("Thank you for using the calculator!");
            Console.ReadKey();
        }
    }
}
