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
      $scope.input = math.evaluate($scope.calculation + $scope.input) + '';
      $scope.calculation = "";
    }
  };
});
