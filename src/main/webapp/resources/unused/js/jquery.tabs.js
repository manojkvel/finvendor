jQuery(document).ready(function() {
    jQuery('.tabs .tab-links a').on('click', function(e)  {
        var currentAttrValue = jQuery(this).attr('href');
 
        // Show/Hide Tabs - Fade
       // jQuery('.tabs ' + currentAttrValue).show().siblings().hide();
        
     // Show/Hide Tabs - Normal
        //jQuery('.tabs ' + currentAttrValue).fadeIn(400).siblings().hide();
        
     // Show/Hide Tabs - Slide
        jQuery('.tabs ' + currentAttrValue).siblings().slideUp(400);
        jQuery('.tabs ' + currentAttrValue).delay(400).slideDown(400);
 
        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
 
        e.preventDefault();
    });
});