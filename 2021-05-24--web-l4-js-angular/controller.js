function log(x) {
  console.log(x);
}

angular.module("App", []).controller("Controller", function ($scope) {
  $scope.calculation = "";
  $scope.input = "0";
  $scope.memory = "0";
  $scope.averageArray = [];

  $scope.MemoryDisplay = function () {
    if ($scope.memory === "0") {
      return "";
    } else {
      return $scope.memory;
    }
  };

  $scope.HandleInput = function (action) {
    log("________ action: " + action);

    // 0-9
    if (!isNaN(+action)) {
      log("is a number");
      if ($scope.input === "0") $scope.input = action;
      else $scope.input += action;
    }

    // memory
    {
      if (action === "MC") {
        $scope.memory = "0";
      }
      if (action === "MR") {
        $scope.input = $scope.memory;
      }
      if (action === "MS") {
        $scope.memory = $scope.input;
      }
      if (action === "M") {
      }
      if (action === "M+") {
        $scope.memory = math.evaluate(`${$scope.memory} + ${$scope.input}`);
      }
      if (action === "M-") {
        $scope.memory = math.evaluate(`${$scope.memory} - ${$scope.input}`);
      }
    }

    // x²
    if (action === "x²") {
      $scope.input = $scope.input * $scope.input;
    }

    // xʸ
    if (action === "xʸ") {
      if ($scope.calculation === "") {
        $scope.calculation = $scope.input;
        $scope.input = "0";
      } else {
        $scope.input = Math.pow(
          math.evaluate($scope.calculation),
          $scope.input
        );
        $scope.calculation = "";
      }
    }

    // ↑
    if (action === "↑") {
      // $scope.calculation = $scope.input;
      // $scope.input = "0";
      $scope.calculation = $scope.calculation + $scope.input;
      $scope.input = "0";
    }

    // C
    if (action === "C") {
      $scope.input = "0";
      $scope.calculation = "";
    }

    // CE
    if (action === "CE") {
      $scope.input = "0";
    }

    // ⌫
    if (action === "⌫") {
      $scope.input = $scope.input.slice(0, -1);
      if ($scope.input === "") $scope.input = "0";
    }

    // π
    if (action === "π") {
      $scope.input = "π";
    }

    // + - * /
    ["+", "-", "*", "/"].forEach((op) => {
      if (action === op) {
        // $scope.calculation += $scope.input;
        $scope.calculation += op;
        $scope.input = "0";
      }
    });

    // .
    if (action === "." && !$scope.input.includes(".")) {
      $scope.input += ".";
    }

    // ±
    if (action === "±" && $scope.input !== "0") {
      if ($scope.input[0] === "-") {
        $scope.input = $scope.input.substring(1);
      } else $scope.input = "-" + $scope.input;
    }

    // =
    if (action === "=") {
      log("evaluating...");
      log("input: " + $scope.input);
      log("calculation: " + $scope.calculation);

      $scope.calculation = $scope.calculation.replace("π", "3.14159");
      $scope.input = $scope.input.replace("π", "3.14159");

      // $scope.input = math.evaluate($scope.calculation + $scope.input) + "";
      // $scope.input = math.evaluate($scope.calculation + "+" + $scope.input) + "";
      // $scope.input = math.evaluate($scope.calculation.concat($scope.input)) + "";
      // $scope.input = math.evaluate($scope.calculation + " " + $scope.input) + "";
      $scope.input = math.evaluate($scope.calculation) + "";
      $scope.calculation = "";
    }

    // √
    if (action === "√") {
      $scope.input = Math.sqrt($scope.input);
    }

    // 10ˣ
    if (action === "10ˣ") {
      $scope.input = Math.pow(10, $scope.input);
    }

    // log
    if (action === "log") {
      $scope.input = Math.log($scope.input);
    }

    // Exp
    if (action === "Exp") {
      $scope.input = Math.exp($scope.input);
    }

    // Mod
    if (action === "Mod") {
      $scope.input = $scope.calculation % $scope.input;
    }

    // n!
    if (action === "n!") {
      $scope.input = math.factorial($scope.input);
    }

    // (
    if (action === "(") {
      $scope.calculation = "(" + $scope.calculation + "";
    }

    // )
    if (action === ")") {
      $scope.calculation = "" + $scope.calculation + ")";
    }

    // )
    if (action === "X̄+") {
$scope.averageArray.push($scope.input)
    }

    // )
    if (action === "X̄=") {
      let total = 0;
      for (let i = 0; i < $scope.averageArray.length; i++) {
        total += +$scope.averageArray[i];
      }
      let avg = total / $scope.averageArray.length;

      $scope.input = "" + avg;
    }
  };
});
