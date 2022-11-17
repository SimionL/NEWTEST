export interface Account {
    name: string;
	surname: string;
	balance: string;
	customerID: string;
	initialCredit: number;
	isCurrentAccount: boolean;
	transactions: [];
}