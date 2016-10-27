/*  ============================================================================

 Copyright (C) 2006-2016 Talend Inc. - www.talend.com

 This source code is available under agreement available at
 https://github.com/Talend/data-prep/blob/master/LICENSE

 You should have received a copy of the agreement
 along with this program; if not, write to Talend SA
 9 rue Pages 92150 Suresnes, France

 ============================================================================*/

export default class ModalActionsService {
	constructor(StateService) {
		'ngInject';
		this.StateService = StateService;
	}

	dispatch(action) {
		switch (action.type) {
		case '@@modal/SHOW':
			this.StateService[action.payload.method]();
			break;
		}
	}
}
