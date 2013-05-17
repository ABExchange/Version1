/*******************************************************************************
 * $('.textfield').blur(function(e) { if( !$(this).val() ) {
 * $(this).parents('p').addClass('warning'); $('.registerwarning').show();
 * e.preventDefault(); } });
 ******************************************************************************/

$(document).ready(function() {
	$('div .trade').hide();
	$('div .account').hide();
	$('div .market').hide();
	$('div .buybitcoins').show();
	$('div .accountBalance').show();
	$('div .marketUSD').show();
	
	$('.signupbutton').click(function(e) {
		
		$('.registerwarning').hide();
		$('.matchingvalues').hide();
		
		$('.textfield').each(function() {
			
			if (!$(this).val()) {
				/** $(this).parents('.registerwarning').show(); * */
				/** $('.registerwarning').show(); * */
				$(this).next('.registerwarning').show();
				$(this).next().next('.registerwarning').show();
				e.preventDefault();
			}
		});
		
		pass = $('#signUpPass').val();
		passConfirm = $('#signUpPassConfirm').val();
		if (String(pass) != String(passConfirm)){
			/** $(this).next('.matchingvalues').show(); * */
			$('.matchingvalues').show();
			e.preventDefault();
		}
	});
	
	
	/***
	 * Buy Button Login
	 * **/
	
$('.signupbuttonbuy').click(function(e) {
		
		$('.registerwarning').hide();
		$('.matchingvalues').hide();
		
		$('.textfieldbuy').each(function() {
			
			if (!$(this).val()) {
				/** $(this).parents('.registerwarning').show(); * */
				/** $('.registerwarning').show(); * */
				$(this).next('.registerwarning').show();
				$(this).next().next('.registerwarning').show();
				e.preventDefault();
			}
		});
	});

/****
 *The Sell Button Login
 * 
 **/
$('.signupbuttonsell').click(function(e) {
	
	$('.registerwarning').hide();
	$('.matchingvalues').hide();
	
	$('.textfieldsell').each(function() {
		
		if (!$(this).val()) {
			/** $(this).parents('.registerwarning').show(); * */
			/** $('.registerwarning').show(); * */
			$(this).next('.registerwarning').show();
			$(this).next().next('.registerwarning').show();
			e.preventDefault();
		}
	});
});

	/***************************************************************************
	 * $(".edgetoedge").removeClass("highlight");
	 **************************************************************************/
	$('.nav-tabs>li').click(function(e){
		$('.nav-tabs>li').removeClass('active');
		/** alert($(this).html()); * */
		$('.trade').hide();
		$('.account').hide();
		$('.market').hide();
		
		$('div .'+$(this).attr('class')).show();
		$(this).addClass('active');
		e.preventDefault();
		
	});
	
	$('.nav-tabs>span').click(function(e){
		$('.nav-tabs>span').removeClass('active');
		/** alert($(this).html()); * */
		$('.trade').hide();
		$('.account').hide();
		$('.market').hide();
		
		$('div .'+$(this).attr('class')).show();
		$(this).addClass('active');
		e.preventDefault();
		
	});
	
	//Perform the computations when the quantity input loses focus
	$('#buyQuantity').blur(function(e){
		var quantity = $('#buyQuantity').val();
		var price;
		price = $("#buyPrice").val();
		
		var rate = (0.5 / 100);
		var total;
		var fee;
		if (quantity && price){
			
			price = $("#buyPrice").val();
			fee = quantity * price * rate;
			$('#buyFee').val(fee);
			
			total = ((quantity * price) + fee);
			
			$('#buyTotal').val(total);
		}
		
		
		
	});
	//Perform computations when price loses focus
	$('#buyPrice').blur(function(e){
		var quantity = $('#buyQuantity').val();
		var price;
		price = $("#buyPrice").val();
		var rate = (0.5 / 100);
		var total;
		var fee;
		if (quantity && price){
			
			price = $("#buyPrice").val();
			fee = quantity * price * rate;
			$('#buyFee').val(fee);
			
			total = ((quantity * price) + fee);
			
			$('#buyTotal').val(total);
		}
		
		
		
	});
	
	
	//Perform the computations when the quantity input loses focus
	//This if for selling
	$('#sellQuantity').blur(function(e){
		var quantity = $('#sellQuantity').val();
		var price;
		var rate = (0.5 / 100);
		var total;
		var fee;
		if (quantity){
			
			price = $("#sellPrice").val();
			fee = quantity * price * rate;
			$('#sellFee').val(fee);
			
			total = ((quantity * price) + fee);
			
			$('#sellTotal').val(total);
		}		
	});
	
	//Perform when price loses focus
	$('#sellPrice').blur(function(e){
		var quantity = $('#sellQuantity').val();
		var price;
		price = $("#sellPrice").val();
		var rate = (0.5 / 100);
		var total;
		var fee;
		if (quantity && price){
			
			price = $("#sellPrice").val();
			fee = quantity * price * rate;
			$('#sellFee').val(fee);
			
			total = ((quantity * price) + fee);
			
			$('#sellTotal').val(total);
		}		
	});
	
	$("#bankAddToggle").click(function(e){
		$("#bankAdd").slideToggle();
		
		if ($("#bankAddToggle").html() == 'Close'){
			$("#bankAddToggle").html('Show');
		} else{
			$("#bankAddToggle").html('Close');
		}
		
	});

	
	
});

function matchValues(first, second){
	
	if (String(first) != String(second)){
		return false;
	} else{
		return true;
	}
	
}
/*******************************************************************************
 * $(function() { $('.signupbutton').click(function(e) {
 * $('textfield').each(function() { if (!$(this).val()) {
 * 
 * $('.registerwarning').show(); alert('Got issues !!!'); e.preventDefault(); }
 * }); }); });
 ******************************************************************************/

/*******************************************************************************
 * $(document).ready(
 * 
 * function() { $('.home-signup').submit(function() { alert('Things here !!');
 * if (!$('.textfield').val()) { $('.registerwarning').show(); return false; }
 * }); });
 ******************************************************************************/
