function log(x) {
  console.log(x);
}

angular.module("App", []).controller("Controller", function ($scope) {
  $scope.calculation = "";
  $scope.input = "0";

  $scope.HandleInput = function (action) {
    log("________ action: " + action);

    // 0-9
    if (!isNaN(+action)) {
      log("is a number");
      if ($scope.input === "0") $scope.input = action;
      else $scope.input += action;
    }

    // .
    if (action === ".") {
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
      $scope.input = 'π'
    }

    // + - * /
    ["+", "-", "*", "/"].forEach((op) => {
      if (action === op) {
        $scope.calculation += $scope.input;
        $scope.calculation += op;
        $scope.input = "0";
      }
    });

    // .
    if (action === "." && !$scope.input.includes(".")) {
      $scope.input += ".";
    }

    // =
    if (action === "=") {
      log("evaluating...");
      log("input: " + $scope.input);
      log("calculation: " + $scope.calculation);

      $scope.calculation = $scope.calculation.replace('π', '3.14159')
      $scope.input = $scope.input.replace('π', '3.14159')

      $scope.input = math.evaluate($scope.calculation + $scope.input) + "";
      // $scope.calculation = "";
    }
  };
});
