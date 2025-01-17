package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        ProjectType projectType = ProjectType.INTERNAL;
        Project project = new Project(projectType,"Project A");
        // when
        ExpenseType expenseTypeExpected = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        ExpenseType expenseType = ExpenseType.INTERNAL_PROJECT_EXPENSE;
        // then
        Assertions.assertEquals(expenseTypeExpected,expenseType);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        ProjectType projectType = ProjectType.EXTERNAL;
        Project project = new Project(projectType,"Project A");
        // when
        ExpenseType expenseTypeExpected = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        ExpenseType expenseType = ExpenseType.EXPENSE_TYPE_A;
        // then
        Assertions.assertEquals(expenseTypeExpected,expenseType);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        ProjectType projectType = ProjectType.EXTERNAL;
        Project project = new Project(projectType,"Project B");
        // when
        ExpenseType expenseTypeExpected = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        ExpenseType expenseType = ExpenseType.EXPENSE_TYPE_B;
        // then
        Assertions.assertEquals(expenseTypeExpected,expenseType);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        ProjectType projectType = ProjectType.EXTERNAL;
        Project project = new Project(projectType,"Project C");
        // when
        ExpenseType expenseTypeExpected = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        ExpenseType expenseType = ExpenseType.OTHER_EXPENSE;
        // then
        Assertions.assertEquals(expenseTypeExpected,expenseType);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        ProjectType projectType = ProjectType.UNEXPECTED_PROJECT_TYPE;
        Project project = new Project(projectType,"Project C");
        // when
        try {
            ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        }catch (UnexpectedProjectTypeException e) {
            e.getMessage();
        // then
        Assertions.assertEquals("You enter invalid project type",e.getMessage());
        }

    }
}