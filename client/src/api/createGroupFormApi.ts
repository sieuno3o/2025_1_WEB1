import api from './instance';
import { AxiosResponse } from 'axios';

export enum Category {
	어학 = '어학',
	IT = 'IT',
	공무원 = '공무원',
	자격증 = '자격증',
	취업준비 = '취업준비',
	자기계발 = '자기계발',
	기타 = '기타',
	학교 = '학교',
}

export enum Region {
	서울특별시 = '서울특별시',
	부산광역시 = '부산광역시',
	대구광역시 = '대구광역시',
	인천광역시 = '인천광역시',
	광주광역시 = '광주광역시',
	대전광역시 = '대전광역시',
	울산광역시 = '울산광역시',
	세종특별자치시 = '세종특별자치시',
	경기도 = '경기도',
	강원도 = '강원도',
	충청북도 = '충청북도',
	충청남도 = '충청남도',
	전라북도 = '전라북도',
	전라남도 = '전라남도',
	경상북도 = '경상북도',
	경상남도 = '경상남도',
	제주특별자치도 = '제주특별자치도',
	해당없음 = '해당없음',
}

export enum StudyType {
	온라인 = '온라인',
	오프라인 = '오프라인',
	혼합 = '혼합',
}

interface CreateGroupParams {
	name: string; // 그룹명
	maxMembers: number; // 최대 멤버 수
	notice: string; // 공지사항
	meetingDays: string; // (ex: '월 3일' or '주 2회')
	meetingTime: string; // (ex: '오전', '저녁' 등)
	meetingType: StudyType; // 대면/비대면
	region: Region; // 지역
	category: Category; // 분야
	type: string; // 분야 내 세부 유형 (예: 경찰행정)
}

interface CreateGroupResponse {
	message: string;

export const createStudyGroup = async (
	data: CreateGroupParams,
): Promise<CreateGroupResponse> => {
	try {
		const token =
			localStorage.getItem('accessToken') ||
			sessionStorage.getItem('accessToken');

		if (!token) throw new Error('인증 토큰이 없습니다.');

		const response = await api.post('/api/studygroup/create', data, {
			headers: {
				Authorization: `Bearer ${token}`,
				'Content-Type': 'application/json',
			},
		});

		return response.data;
	} catch (error: any) {
		throw new Error(
			`스터디 그룹 생성에 실패했습니다: ${error?.message || error}`,
		);
	}
};
