import Ember from 'ember';

export default Ember.Controller.extend({
	session: Ember.inject.service(),

	actions: {
		authenticate: function() {
			let credentials = this.getProperties('identification', 'password'),
			authenticator = 'authenticator:jwt';

      const flashMessages = Ember.get(this, 'flashMessages');

			this.get('session').authenticate(authenticator, credentials).then(() => {
        flashMessages.success('Login realizado com sucesso!');
			}, () => {
        flashMessages.danger('Não foi possível autenticar, por favor, verifique seus dados.');
			});
		}
	}
});
