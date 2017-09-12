import Ember from 'ember';
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

      /* Phone Mask */
      $('.phone-mask').inputmask({
        mask: ["(99) 9999-9999", "(99) 99999-9999"],
        clearMaskOnLostFocus: false
      });

    });
  }
});
