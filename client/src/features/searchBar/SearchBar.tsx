import { Search } from 'lucide-react';
import './SearchBar.scss';
import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';

const SearchBar = () => {
	return (
		<div className="search-bar flex-center">
			<Search className="text-lime-500 w-5 h-5" />
			<input
				type="text"
				placeholder="검색"
				className="ml-2 w-full bg-transparent outline-none placeholder-gray-300"
			/>
		</div>
	);
};

export default SearchBar;
