using System.Collections.Generic;
using System.Linq;

namespace agents_app.Code
{
    public static class AgentManager
    {
        public static List<Agent> Agents = new();
        public static Parameters Parameters;

        static AgentManager()
        {
            Agents.Add(new Agent());
            Agents.Add(new Agent());
            Agents.Add(new Agent());
        }

        public static void SetParameters(Parameters parameters)
        {
            Parameters = parameters;
        }

        public static IEnumerable<Computer> GetFoundComputers()
        {
            return Agents.Select(agent => agent.GetComputer(Parameters)).ToList();
        }

        public static void GiveFeedback(Computer chosenComputer)
        {
            
        }
    }
}