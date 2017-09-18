import Ember from 'ember';
/* globals Inputmask */

const { $ } = Ember;

export default Ember.Component.extend({
  didInsertElement: function () {
    Ember.run.scheduleOnce('afterRender', this, function () {
      /* CPF Mask */
      $('.cpf-mask').inputmask({
        mask: "999.999.999-99",
        clearMaskOnLostFocus: false
      });

      /* Zip Code Mask */
      $('.zip-code-mask').inputmask({
        mask: "99999-999",
        clearMaskOnLostFocus: false
      });

      Inputmask.extendAliases({
        'phoneAlias': {
          mask: ["(99) 9999-9999", "(99) 99999-9999"],
          clearMaskOnLostFocus: false
        }
      });

      /* Uses MutationObserver */
      $(document).arrive(".phone-mask", function() {
        $(this).inputmask({
          alias: 'phoneAlias'
        });
      });

      /* This is the common: $(":input").inputmask(); */
      $(".phone-mask").inputmask();
    });
  }
});
