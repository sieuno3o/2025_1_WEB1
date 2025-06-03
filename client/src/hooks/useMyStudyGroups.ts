import { useEffect, useState } from 'react';
import { fetchMyGroups } from 'api/myGroupsApi';
import { StudyGroup } from 'api/studyGroupApi';

type Group = {
	id: number;
	name: string;
	meetingDays: string;
	meetingTime: string;
	meetingType: string;
	currentMembers: number;
	maxMembers: number;
	region: string;
	category: string;
	type: string;
};

export const useMyStudyGroups = () => {
	const [groups, setGroups] = useState<StudyGroup[]>([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState<string | null>(null);

	useEffect(() => {
		const load = async () => {
			setLoading(true);
			try {
				const result = await fetchMyGroups();
				setGroups(result ?? []);
				console.log('✅ 내 그룹 응답:', result);
			} catch (err) {
				console.error('내 그룹 목록 로딩 실패', err);
				setError('내 그룹 데이터를 불러오는 중 오류가 발생했습니다.');
			} finally {
				setLoading(false);
			}
		};

		load();
	}, []);

	return { groups, loading, error };
};
