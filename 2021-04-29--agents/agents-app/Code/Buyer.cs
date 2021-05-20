using System.Collections.Generic;
using System.Linq;

namespace agents_app.Code
{
    public class Buyer
    {
        public Computer GetComputer()
        {
            var userInputParameters = World.UserInputParameters;
            var computersSuggestedBySellers = new List<Computer>();

            World.Sellers.ForEach(s =>
                computersSuggestedBySellers.AddRange(s.SuggestComputersForParameters(userInputParameters)));

            // computersSuggestedBySellers.Sort((c1, c2) => c1.Parameters.Cost.CompareTo(c2.Parameters.Cost));

            computersSuggestedBySellers.Sort((c1, c2)
                => c1.Parameters.DivergenceFromUserInputParameters(userInputParameters)
                    .CompareTo(c2.Parameters.DivergenceFromUserInputParameters(userInputParameters)));

            return computersSuggestedBySellers.First();
        }

        public void GiveFeedback(Computer chosenComputer)
        {
        }
    }
}