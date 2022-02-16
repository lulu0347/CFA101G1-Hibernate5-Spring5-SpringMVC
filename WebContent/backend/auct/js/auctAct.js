<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
		$.datetimepicker.setLocale('zh');
		$('#pickdate')
				.datetimepicker(
						{
							theme : '', // theme: 'dark',
							timepicker : false, // timepicker:true,
							step : 60, // step: 60 (這是timepicker的預設間隔60分鐘)
							format : 'Y-m-d', // format:'Y-m-d H:i:s',
							value : '<fmt:formatDate value="${auctActVO.auctStartTime}" pattern="yyyy-MM-dd" />',
							minDate : new Date(),
						});

		$('#pickdate1')
				.datetimepicker(
						{
							theme : '', // theme: 'dark',
							timepicker : false, // timepicker:true,
							step : 60, // step: 60 (這是timepicker的預設間隔60分鐘)
							format : 'Y-m-d', // format:'Y-m-d H:i:s',
							value : '<fmt:formatDate value="${auctActVO.auctEndTime}" pattern="yyyy-MM-dd" />',
							minDate : new Date(),
						});
		function loadFile(event, inputName) {
			var output = document.getElementById(inputName + "Show");
			output.src = URL.createObjectURL(event.target.files[0]);
			output.onload = function() {
				URL.revokeObjectURL(output.src) // free memory
			}
		};
</script> 