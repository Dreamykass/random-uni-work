using System.Linq;

namespace agents_app.Code
{
    public class Agent
    {
        public Computer GetComputer(Parameters parameters)
        {
            return Computer.GetAllComputers().Shuffle().First();
        }

        public void GiveFeedback(Computer chosenComputer)
        {
            
        }
    }
}