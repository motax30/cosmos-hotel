import Ember from 'ember';

export default Ember.Controller.extend({
	actions: {
		save(record) {
      const flashMessages = Ember.get(this, 'flashMessages');
			let self = this;
			record.save().then(function(savedRecord) {
        let datetime = new Date().toLocaleString('pt-br');
        flashMessages.success(`Recepcionista ${savedRecord.get('userName')} editado com sucesso em ${datetime}!`);
				self.transitionToRoute('app.receptionists.show', savedRecord.id);
			});
		}
	}
});
