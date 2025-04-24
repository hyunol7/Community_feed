// Set Bootstrap-like default font
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Chart Initializer Function
const initChart = (labels, data, maxValue) => {
  const ctx = document.getElementById("myBarChart").getContext("2d");

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: labels,
      datasets: [{
        label: "일별 가입 수",
        backgroundColor: "rgba(2,117,216,1)",
        borderColor: "rgba(2,117,216,1)",
        data: data,
      }],
    },
    options: {
      scales: {
        xAxes: [{
          time: {
            unit: 'day'
          },
          gridLines: {
            display: false
          },
          ticks: {
            maxTicksLimit: 6
          }
        }],
        yAxes: [{
          ticks: {
            min: 0,
            max: maxValue + 1,
            maxTicksLimit: 5
          },
          gridLines: {
            display: true
          }
        }],
      },
      legend: {
        display: false
      }
    }
  });
}
