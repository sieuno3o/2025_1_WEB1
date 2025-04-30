import api from './instance';
import { AxiosResponse } from 'axios';

interface CreateGroupParams {
	name: string;
	maxMembers: number;
	notice: string;
	meetingDays: string;
	meetingTime: string;
}

export const createStudyGroup = async (
	data: CreateGroupParams,
): Promise<AxiosResponse> => {
	try {
		const response = await api.post('/api/studygroup/create', data);
		return response;
	} catch (error) {
		throw new Error(`스터디 그룹 생성에 실패했습니다: ${error}`);
	}
};
